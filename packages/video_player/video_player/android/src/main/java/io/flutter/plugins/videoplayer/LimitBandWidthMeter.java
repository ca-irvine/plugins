package io.flutter.plugins.videoplayer;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.TransferListener;

final class LimitBandWidthMeter implements BandwidthMeter {
  private final DefaultBandwidthMeter defaultBandwidthMeter;

  long limitBitrate = Long.MAX_VALUE;

  LimitBandWidthMeter(Context context) {
    this.defaultBandwidthMeter = new DefaultBandwidthMeter.Builder(context).build();
  }

  @Override
  public long getBitrateEstimate() {
    return Math.min(defaultBandwidthMeter.getBitrateEstimate(), limitBitrate);
  }

  @Nullable
  @Override
  public TransferListener getTransferListener() {
    return defaultBandwidthMeter.getTransferListener();
  }

  @Override
  public void addEventListener(Handler eventHandler, EventListener eventListener) {
    defaultBandwidthMeter.addEventListener(eventHandler, eventListener);
  }

  @Override
  public void removeEventListener(EventListener eventListener) {
    defaultBandwidthMeter.removeEventListener(eventListener);
  }
}
