package main.configuration;

public class ProfilingFeatureSwitcher implements ProfilingFeatureSwitcherMBean{
    private boolean profilingFeatureEnabled = true;

    public boolean isProfilingFeatureEnabled() {
        return profilingFeatureEnabled;
    }

    public void setProfilingFeatureEnabled(boolean profilingFeatureEnabled) {
        this.profilingFeatureEnabled = profilingFeatureEnabled;
    }
}
