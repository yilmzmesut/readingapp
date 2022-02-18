package org.yilmzmesut.readingapp.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yilmzmesut.readingapp.config.JwtUtils;
import org.yilmzmesut.readingapp.entity.Customer;
import org.yilmzmesut.readingapp.exception.ReadingAppErrorCode;
import org.yilmzmesut.readingapp.exception.ReadingAppException;
import org.yilmzmesut.readingapp.mapper.CustomerMapper;
import org.yilmzmesut.readingapp.model.dto.CustomerDTO;
import org.yilmzmesut.readingapp.model.request.LoginCustomerRequest;
import org.yilmzmesut.readingapp.model.request.SignUpRequest;
import org.yilmzmesut.readingapp.model.response.TokenResponse;
import org.yilmzmesut.readingapp.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final CustomerMapper customerMapper;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository,
                           AuthenticationManager authenticationManager,
                           CustomerMapper customerMapper,
                           JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.authenticationManager = authenticationManager;
        this.customerMapper = customerMapper;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerDTO findById(Long id) {
        var customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customerMapper.toDTO(customer.get());
        }
        throw new ReadingAppException(ReadingAppErrorCode.ENTITY_NOT_FOUND, "customer is not found by id=" + id);
    }

    public CustomerDTO findByEmail(String email) {
        return customerMapper.toDTO(customerRepository.findByEmail(email).orElse(null));
    }

    public CustomerDTO signUp(SignUpRequest signUpRequest) {
        var customer = findByEmail(signUpRequest.getEmail());
        if (customer != null) {
            throw new ReadingAppException(ReadingAppErrorCode.EXISTING_USER, ReadingAppErrorCode.EXISTING_USER.getDescription() + " email = " + customer.getEmail());
        }
        var savedCustomer = customerRepository.save(Customer.builder()
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build());
        return customerMapper.toDTO(savedCustomer);
    }

    public TokenResponse signIn(LoginCustomerRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var jwt = jwtUtils.generateJwtToken(authentication);
        return TokenResponse.builder()
                .accessToken(jwt)
                .tokenType("Bearer")
                .expiresIn(jwtUtils.getExpirationDateFromToken(jwt).getTime())
                .build();
    }
}
