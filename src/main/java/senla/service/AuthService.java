package senla.service;

import senla.dto.security.AuthRequest;
import senla.dto.security.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse reg(AuthRequest authRequest);
}
