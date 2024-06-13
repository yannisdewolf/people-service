package org.dummy.world.peopleservice.service;

import org.springframework.stereotype.Service;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    private final FeatureManager featureManager;

    public FeatureService(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    public Set<String> getFeatures() {
        return featureManager.getFeatures().stream().map(Feature::name).collect(Collectors.toSet());
    }

    public void activateFeature(String featureName) {
        Feature feature = featureManager.getFeatures().stream()
                .filter(f -> f.name().equals(featureName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No feature found with name " + featureName));
        featureManager.enable(feature);
    }

}
