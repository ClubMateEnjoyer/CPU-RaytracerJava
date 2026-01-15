package cgg;

import tools.Color;
import static tools.Color.*;
import static tools.Functions.*;
import tools.ISampler;
import tools.Vec2;

public record SuperSampler(ISampler sampler, SAMPLINGTYPE type, int numSamples) implements ISampler {

    public enum SAMPLINGTYPE {
        POINT,
        GRID,
        RANDOM,
        STRATIFIED
    };

    public SuperSampler {
        numSamples = (int)Math.floor(Math.sqrt(numSamples));
    }

    @Override
    public Color getColor(Vec2 at) {

        switch (type) {
            default: {
                return sampler.getColor(at);
            }
            case POINT: {
                return sampler.getColor(
                    vec2(at.u() + 0.5, at.v() + 0.5)
                );
            }
            case GRID: {
                Color color = black;
                for(int i=0; i<numSamples; ++i)
                for(int j=0; j<numSamples; ++j) {
                    double du = (i + 0.5) / numSamples;
                    double dv = (j + 0.5) / numSamples;
                    Vec2 offset = vec2(at.u() + du, at.v() + dv);
                    color = add(color, sampler.getColor(offset));                    
                }
                return divide(color, numSamples * numSamples);
            }
            case RANDOM: {
                Color color = black;
                for(int i=0; i<numSamples; ++i)
                for(int j=0; j<numSamples; ++j) {
                    double du = random();
                    double dv = random();
                    Vec2 offset = vec2(at.u() + du, at.v() + dv);
                    color = add(color, sampler.getColor(offset));
                }
                return divide(color, numSamples * numSamples);
            }
            case STRATIFIED: {
                Color color = black;
                for (int i = 0; i < numSamples; ++i) 
                for (int j = 0; j < numSamples; ++j) {
                    double du = (i + random()) / numSamples;
                    double dv = (j + random()) / numSamples;
                    Vec2 offset = vec2(at.u() + du, at.v() + dv);
                    color = add(color, sampler.getColor(offset));
                }  
                return divide(color, numSamples * numSamples);
            }
        } 
    }
}
