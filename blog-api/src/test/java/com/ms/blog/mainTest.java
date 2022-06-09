package com.ms.blog;

import com.alibaba.fastjson.JSONObject;
import com.ms.blog.utils.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@Slf4j
@SpringBootTest
public class mainTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){
        try {
            System.out.println("213234");
            Long aLong = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
            log.info("记录总数：{}",aLong);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     *  用测试生成的公钥，私钥赋值
     */
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcjsbthbLOsbaMobw8fQIgq+NDTOxtE+tdPN7t4O5xpILz051VMjdcZqf74icL+BIxtdmj+IRM24UA0cvns+YxBI1uCZD9rC90SyQAVBDWfujR/WRczqS/Npu+2LdL0omRqbUHurYJoVej7dT9Z9mCKXfeGhn8gm+3BSd2wPffzwIDAQAB";

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJyOxu2Fss6xtoyhvDx9AiCr40NM7G0T61083u3g7nGkgvPTnVUyN1xmp/viJwv4EjG12aP4hEzbhQDRy+ez5jEEjW4JkP2sL3RLJABUENZ+6NH9ZFzOpL82m77Yt0vSiZGptQe6tgmhV6Pt1P1n2YIpd94aGfyCb7cFJ3bA99/PAgMBAAECgYBFzLZOQbSlyL/N1XIaOdXQATKy18H1I9Nk33W48vb8Pc+QrAFScbATB0XP3FVyVaiu9xfQrkU2Cw0yf1b8t81hyi+yfvjumYGwRzRqYK7XpZae1meOdd0lHeqZ1LOTbD7qVQMu4rSdmGq3Z01AFw2vpZPdY8EnOMKx/AfNduvE4QJBAO3DslO3p6gsd6bezeSxYMKcdempFpLX/brVTBMMWEoo5FBfq63izXCMCW6KRscKL45PTam8+dO2ZH9DEIoNxP8CQQCokKfFsdNjRaeIvp3QbhRvqOchH9JVdOw2YOEennKZ6AKAeZbFJv3ezZ7FzEzXEALJhzbSWKgApxeR3vQ/k9UxAkA4nTF4F7H3LGdtN35FJytIC6NT+OwkGQQUmbdcjcL0HZqHvNAPPoL2epHAXXKO8FD0jNOMSDAghjyVxO1KQg8XAkEAlKb0/ZfjpmEv7JzFSaE6LnpSUdjhQXGaOh5XiQwMXoPHaQtRFc5cBddpVF/lx6cb/iNbCkca6XBxjbeDyGmTkQJADNKcIsR+J+esJKAhT7qWaeZC/G2PtpWSnqCgzebmysEntK3oTBlPtLHqR7nZZ5dThkQeR+cdXNY8ZkqGmPtd0Q==";


    /**
     *  生成公钥私钥
     */
    @Test
    public void generateRsaKey() {
        Map<String, String> map = RSAUtils.generateRasKey();
        System.out.println("随机生成的公钥为:" + map.get(RSAUtils.PUBLIC_KEY));
        System.out.println("随机生成的私钥为:" + map.get(RSAUtils.PRIVATE_KEY));

    }

    /**
     * 加密: YjFABCZ/yAsxocwuOMptQLYxoFrVNvyiNCX/YEBAQnVJi9gTkt8NXkG4s39TIyanUPZc6u872NUkF86R0BYkic/IJBDceg+FbuQEL++fTiahT2EAL30d1fUB1mgFCrxG9Qcl5UPUEgY37b1YA8AZGwZNDMvtEHbAqt4nhY9ZvSE=
     */
    @Test
    public void testEncrypt() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "rosh");
        jsonObject.put("password", "123456");
        String str = jsonObject.toJSONString();
        String encrypt = RSAUtils.encrypt(str, PUBLIC_KEY);
        System.out.println(encrypt);
    }

    @Test
    public void testDecrypt() {

        String decrypt = RSAUtils.decrypt("YjFABCZ/yAsxocwuOMptQLYxoFrVNvyiNCX/YEBAQnVJi9gTkt8NXkG4s39TIyanUPZc6u872NUkF86R0BYkic/IJBDceg+FbuQEL++fTiahT2EAL30d1fUB1mgFCrxG9Qcl5UPUEgY37b1YA8AZGwZNDMvtEHbAqt4nhY9ZvSE=",
                PRIVATE_KEY);

        System.out.println(decrypt);
    }





}
