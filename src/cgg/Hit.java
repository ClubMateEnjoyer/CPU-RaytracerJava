package cgg;

import tools.*;

public record Hit(
    double t,
    Vec3 pos,
    Vec3 normal,
    Vec2 uv,
    IMaterial material,
    Vec3 incident
) {}
