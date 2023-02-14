package com.tuimm.learningpath.domain.weatherconditions;

public enum WeatherCondition {
    PARTLY_SUNNY {
        @Override
        public boolean isBadWeather() {
            return false;
        }
    }, PARTLY_CLOUDY {
        @Override
        public boolean isBadWeather() {
            return false;
        }
    }, RAINY {
        @Override
        public boolean isBadWeather() {
            return true;
        }
    }, SNOWY {
        @Override
        public boolean isBadWeather() {
            return true;
        }
    }, CLOUDY {
        @Override
        public boolean isBadWeather() {
            return false;
        }
    }, SUNNY {
        @Override
        public boolean isBadWeather() {
            return false;
        }
    }, WINDY {
        @Override
        public boolean isBadWeather() {
            return false;
        }
    };
    public abstract boolean isBadWeather();
}
