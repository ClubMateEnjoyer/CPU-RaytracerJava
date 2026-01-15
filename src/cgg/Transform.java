package cgg;

import tools.Mat44;
import static tools.Functions.*;

public class Transform {

    Mat44 localToWorld;
    Mat44 worldToLocal;
    Mat44 localToWorldNormal;

    public Transform(Mat44 transform) {
        this.localToWorld = transform;
        this.worldToLocal = invert(transform);
        this.localToWorldNormal = transpose(this.worldToLocal);
    }

    public Mat44 localToWorld() {
        return localToWorld;
    }

    public Mat44 localToWorldNormal() {
        return localToWorldNormal;
    }

    public Mat44 worldToLocal() {
        return worldToLocal;
    }
}
