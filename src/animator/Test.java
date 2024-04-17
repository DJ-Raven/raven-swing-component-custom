package animator;

/**
 *
 * @author RAVEN
 */
public class Test {

    public static void main(String[] args) {
        Animator ani = new Animator(100, new Animator.TimingTarget() {
            @Override
            public void timingEvent(float fraction) {
                System.out.println(fraction);
            }
        });
        ani.start();
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
        }
    }
}
