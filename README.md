## Wallet Kreppi
### Docker
Se ha generado una imagen de docker con la aplicación de manera publica aquí https://hub.docker.com/repository/docker/juancamilogj/wallet-kreppi/general

### Aws
Se configuró una instancia ec2 en aws para desplegar la imagen de docker, la instancia esta configurada con una ip pública y un puerto 8970 abíerto para poder acceder a la aplicación.

### Endpoints
Dejo el curl para probar la aplicación:
```
curl --location 'http://18.191.14.53:8970/wallet/balance?document=123456789&nPhone=123456789'
```

# wallet-kreppi
