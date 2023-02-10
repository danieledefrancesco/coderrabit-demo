package com.tuimm.leaarningpath.domain.weatherconditions;

public enum WeatherCondition {
    PARTLY_SUNNY {
        @Override
        public boolean requiresCoverage() {
            return false;
        }
    }, PARTLY_CLOUDY {
        @Override
        public boolean requiresCoverage() {
            return false;
        }
    }, RAINY {
        @Override
        public boolean requiresCoverage() {
            return true;
        }
    }, SNOWY {
        @Override
        public boolean requiresCoverage() {
            return true;
        }
    }, CLOUDY {
        @Override
        public boolean requiresCoverage() {
            return false;
        }
    }, SUNNY {
        @Override
        public boolean requiresCoverage() {
            return false;
        }
    }, WINDY {
        @Override
        public boolean requiresCoverage() {
            return false;
        }
    };
    public abstract boolean requiresCoverage();
}
