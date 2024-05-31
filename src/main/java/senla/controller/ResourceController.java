package senla.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.security.AuthRequest;
import senla.dto.security.AuthResponse;
import senla.service.AuthService;

@RestController
@RequestMapping("/api/v1/resource-controller")
@RequiredArgsConstructor
public class ResourceController {
    private final AuthService authService;


    @PostMapping("/login")
    public AuthResponse loginEndpoint(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/reg")
    public AuthResponse registerEndpoint(@RequestBody AuthRequest authRequest) {
        return authService.reg(authRequest);
    }




}
