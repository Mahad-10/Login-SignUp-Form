package pk.codebase.firstfirebase;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class gettingDeviceTokenService extends Service {
    public gettingDeviceTokenService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
