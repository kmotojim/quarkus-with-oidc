# シナリオ<1>

## Branch切り替え
```
git checkout scenario1
```

## DEVELOPMENT機能で起動
```
# -Dオプションで変数をアプリケーションに受渡し
./mvnw clean package quarkus:dev
```

## hello-page
http://localhost:8080/web/hello-page?myname=kmotojim


# シナリオ<2>

## Branch切り替え
```
git checkout scenario2
```

## GitHub AppのClient IDとSecretを変数化
```
CLIENTID=<Client ID>
SECRET=<Secret>
```

## DEVELOPMENT機能で起動
```
# -Dオプションで変数をアプリケーションに受渡し
./mvnw clean package quarkus:dev -Dquarkus.oidc.client-id=$CLIENTID -Dquarkus.oidc.credentials.secret=$SECRET
```

## hello-page(保護有り)
http://localhost:8080/web/hello-page?myname=kmotojim

## protected-page(保護有り)
http://localhost:8080/web/protected-page

## not-protected-page(保護なし)
http://localhost:8080/web/not-protected-page