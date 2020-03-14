package java.lang;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020/3/12 at 11:53 上午
 */
public class Object {

    private int ii = 0;

    public final native Class<?> getClass();
    public native int hashCode();
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

}
