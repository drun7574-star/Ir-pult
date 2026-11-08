package com.irpult;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class IrPlugin extends CordovaPlugin {
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext cb) throws JSONException {
    ConsumerIrManager m = (ConsumerIrManager) cordova.getActivity().getSystemService(Context.CONSUMER_IR_SERVICE);
    boolean has = m != null && m.hasIrEmitter();
    if (action.equals("has")) {
      cb.success(has ? 1 : 0);
      return true;
    }
    if (action.equals("transmit")) {
      if (!has) {
        cb.error("No IR emitter on this phone");
        return true;
      }
      try {
        int f = args.getInt(0);
        String[] p = args.getString(1).split(",");
        int[] pat = new int[p.length];
        for (int i = 0; i < p.length; i++) pat[i] = Integer.parseInt(p[i].trim());
        m.transmit(f, pat);
        cb.success();
      } catch (Exception e) {
        cb.error(String.valueOf(e.getMessage()));
      }
      return true;
    }
    return false;
  }
}
