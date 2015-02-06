package javax.crypto.spec;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.math.BigInteger;
import java.security.spec.KeySpec;

public class DHPublicKeySpec implements KeySpec {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:55.040 -0500", hash_original_field = "7C667BA619B926582BFC8401F882439D", hash_generated_field = "E87E2F8BC9A92B0C676B9ECF48E707BE")

    private  BigInteger y;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:55.043 -0500", hash_original_field = "36A2346422FAB1A0484079E02615C501", hash_generated_field = "0E1745DDBCB8E3192EFA76F952746D75")

    private  BigInteger p;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:55.045 -0500", hash_original_field = "12F45ECD0F75F4702B861E1DEFDA99B3", hash_generated_field = "5E2139CE4CF026F837A4AD7B2D5946CD")

    private  BigInteger g;

    /**
     * Creates a new <code>DHPublicKeySpec</code> instance with the specified
     * <i>public value</i> <code>y</code>, the <i>prime modulus</i>
     * <code>p</code> and the <i>base generator</i> <code>g</code>.
     *
     * @param y
     *            the public value.
     * @param p
     *            the prime modulus.
     * @param g
     *            the base generator.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:55.048 -0500", hash_original_method = "BEC503CA854BAF195D783D6731B76102", hash_generated_method = "C371817E500B8F6A2040126BFF50B869")
    
public DHPublicKeySpec(BigInteger y, BigInteger p, BigInteger g) {
        this.y = y;
        this.p = p;
        this.g = g;
    }

    /**
     * Returns the <i>public value</i> <code>y</code>.
     *
     * @return the public value <code>y</code>.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:55.050 -0500", hash_original_method = "E902D6151D4FD6CFB5C854DFA08B0568", hash_generated_method = "DFB59F5A3C2E9D3EBF4F764FC762B9D4")
    
public BigInteger getY() {
        return y;
    }

    /**
     * Returns the <i>prime modulus</i> <code>p</code>.
     *
     * @return the prime modulus <code>p</code>.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:55.052 -0500", hash_original_method = "451C630A2F5A52BD2AE63729BE355876", hash_generated_method = "FA39994F0F567A64C38613032682F7D7")
    
public BigInteger getP() {
        return p;
    }

    /**
     * Returns the <i>base generator</i> <code>g</code>;
     *
     * @return the base generator <code>g</code>;
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:55.055 -0500", hash_original_method = "8C38F55FA5DB902C3D9409517AA217E2", hash_generated_method = "FBDB40C3059FF68266098ECF54AD2FB6")
    
public BigInteger getG() {
        return g;
    }
    
}
