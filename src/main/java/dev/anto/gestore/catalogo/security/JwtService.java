package dev.anto.gestore.catalogo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.anto.gestore.catalogo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileReader;
import java.security.KeyFactory;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;

    private Algorithm algorithm;

    @Value("${gestore-catalogo.jwt.issuer}")
    private String issuer;

    @PostConstruct
    @SneakyThrows
    public void init(){
        Security.addProvider(new BouncyCastleProvider());

        //read public key
        var publicKeyFile = ResourceUtils.getFile("classpath:rsapublic.pem");
        var reader = new PemReader(new FileReader(publicKeyFile));

        X509EncodedKeySpec ksPub = new X509EncodedKeySpec(reader.readPemObject().getContent());
        KeyFactory kfPub = KeyFactory.getInstance("RSA");
        RSAPublicKey pub = (RSAPublicKey) kfPub.generatePublic(ksPub);

        //read private key
        var privateKeyFile = ResourceUtils.getFile("classpath:rsaprivate.pem");
        reader = new PemReader(new FileReader(privateKeyFile));

        PKCS8EncodedKeySpec ksPriv = new PKCS8EncodedKeySpec(reader.readPemObject().getContent());
        KeyFactory kfPriv = KeyFactory.getInstance("RSA");
        RSAPrivateKey pvt = (RSAPrivateKey) kfPriv.generatePrivate(ksPriv);

        algorithm = Algorithm.RSA256(pub, pvt);
    }

    public DecodedJWT verify(String jwt){
        try {
            JWTVerifier verifier = JWT.require(this.algorithm)
                    .withIssuer(this.issuer)
                    .build();

            return verifier.verify(jwt);
        } catch (JWTVerificationException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT non valido");
        }
    }

    public UserDetails getUser(DecodedJWT decoded){
        var email = decoded.getSubject();

        return userRepository.findById(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Utente non valido")
        );
    }

    public String createJWT(UserDetails utente){
        return JWT.create()
                .withIssuer(this.issuer)
                .withClaim("sub", utente.getUsername())
                .sign(this.algorithm);
    }

    public String login(String email, String password) {
        var utente = userRepository.findById(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Utente non valido")
        );

        if(BCrypt.checkpw(password, utente.getPassword())){
            return createJWT(utente);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Dati di accesso non corretti");
    }
}
