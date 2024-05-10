package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.config.UserDetailsConfig;
import senla.dto.security.AuthRequest;
import senla.dto.security.AuthResponse;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.RoleRepository;
import senla.repository.api.UserRepository;
import senla.security.JwtService;
import senla.servise.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        User user = Optional.ofNullable(userRepository.findByEmail(authRequest.getEmail())).orElseThrow();
        String jwtToken = jwtService.generateToken(new UserDetailsConfig(user));

        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse reg(AuthRequest authRequest) {
        User user = User.builder()
                .email(authRequest.getEmail())
                .userPassword(passwordEncoder.encode(authRequest.getPassword()))
                .role(roleRepository.findByRoleName(RoleName.CLIENT))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(new UserDetailsConfig(user));

        return AuthResponse.builder().token(jwtToken).build();
    }
}
