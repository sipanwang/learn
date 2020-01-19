package generate.generator;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.nio.charset.Charset;

public class TransFlowGeneratorTest {

    @Test
    public void testHash() {
        HashFunction hf = Hashing.murmur3_128();
        for (int i = 0; i < 100; i++) {
            Hasher hasher = hf.newHasher();
            hasher.putString("xxxxx", Charset.forName("UTF-8"));
            System.out.println(hasher.hash().toString());

        }
    }

}