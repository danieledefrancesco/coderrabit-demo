package com.tuimm.leaarningpath.domain.weatherconditions;

public enum WeatherCondition {
    PARTLY_SUNNY {
        @Override
        boolean requiresCoverage() {
            return false;
        }
    }, PARTLY_CLOUDY {
        @Override
        boolean requiresCoverage() {
            return false;
        }
    }, RAINY {
        @Override
        boolean requiresCoverage() {
            return true;
        }
    }, SNOWY {
        @Override
        boolean requiresCoverage() {
            return true;
        }
    }, CLOUDY {
        @Override
        boolean requiresCoverage() {
            return false;
        }
    }, SUNNY {
        @Override
        boolean requiresCoverage() {
            return false;
        }
    };
    abstract boolean requiresCoverage();
}
