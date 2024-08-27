package NesveSib.Installment.security;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class PasswordEncryptor {

    public String encryptPassword(String password) {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        // Set realistic values for Argon2 parameters
        int parallelism = 2; // Use 2 threads
        int memory = 65536; // Use 64 MB of memory
        int iterations = 3; // Run 3 iterations
        int hashLength = 32; // Generate a 32 byte (256 bit) hash

        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
//                .withSalt(salt)
                .withParallelism(parallelism) // Parallelism factor
                .withMemoryAsKB(memory) // Memory cost
                .withIterations(iterations); // Number of iterations

        generator.init(builder.build());
        byte[] result = new byte[hashLength];
        generator.generateBytes(password.toCharArray(), result);
        return Base64.getEncoder().encodeToString(result);
    }
}
