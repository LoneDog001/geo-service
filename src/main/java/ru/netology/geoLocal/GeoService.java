package ru.netology.geoLocal;

import ru.netology.entity.Location;

public interface GeoService {

    Location byIp(String ip);

    Location byCoordinates(double latitude, double longitude);
}
