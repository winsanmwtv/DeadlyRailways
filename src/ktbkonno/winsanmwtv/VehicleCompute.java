package ktbkonno.winsanmwtv;

public class VehicleCompute {
    Thread vehicleRunner = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                    try {
                        vehicleRunner.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

            }
        }
    });
}
