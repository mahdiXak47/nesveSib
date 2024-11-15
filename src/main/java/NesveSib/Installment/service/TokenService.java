package NesveSib.Installment.service;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

//    @Autowired
//    private TokenRepository tokenRepository;

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
