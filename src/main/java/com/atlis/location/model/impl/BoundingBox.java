/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlis.location.model.impl;

import com.atlis.location.model.LocationModelAbs;

/**
 *
 * @author nf
 */
public class BoundingBox  extends LocationModelAbs{

    MapPoint minPoint;
    MapPoint maxPoint;

    public BoundingBox(MapPoint minPoint, MapPoint maxPoint) {
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }

    public MapPoint getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(MapPoint minPoint) {
        this.minPoint = minPoint;
    }

    public MapPoint getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(MapPoint maxPoint) {
        this.maxPoint = maxPoint;
    }

}
