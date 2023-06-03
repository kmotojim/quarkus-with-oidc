# シナリオ<1>

## Branch切り替え
```
git checkout scenario1
```
## DEVELOPMENT機能で起動
```
# quarkusを開発モードで機能
./mvnw clean package quarkus:dev
```
## hello-page(Githubにログインせずにアクセス可能)
http://localhost:8080/web/hello-page?myname=kmotojim

http://localhost:8080/web/private-page?myname=新宿一郎

## ログイン機能有効化

### GitHub App作成
以下項目を設定
- GitHub App name: <任意の名前>
- Homepage URL: http://localhost:8080
- Callback URL: http://localhost:8080/callback
- Webhook: Disable(Uncheck Active)

GitHub App作成後、Secretを生成する。また、GitHub AppのClient IDとSecretを控えておく。

### pom.xml編集
依存関係の追加
```
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-oidc</artifactId>
</dependency>
```

### application.properties編集
OIDCに関する設定の追加
```
# Configuration file

# OIDCの有効・無効
quarkus.oidc.enabled=true

# Well-Known OpenID Connect Providersの指定
quarkus.oidc.provider=github

# GitHub Apps
quarkus.oidc.client-id=dummy
quarkus.oidc.credentials.secret=dummy

# アプリケーションタイプの指定
quarkus.oidc.application-type=web-app

# Permissionパスの指定(authenticated)
quarkus.http.auth.permission.authenticated.paths=/*
# 指定したPermissionパスのPolicyを指定
quarkus.http.auth.permission.authenticated.policy=authenticated

# リダイレクト URIの指定
quarkus.oidc.authentication.redirect-path=/callback
# リダイレクト後、オリジナルURLにアクセス先を復元
quarkus.oidc.authentication.restore-path-after-redirect=true
```

## GitHub AppのClient IDとSecretを変数化
```
CLIENTID=<Client ID>
SECRET=<Secret>
```

## DEVELOPMENT機能で起動
```
# quarkusを開発モードで機能 & -Dオプションで変数をアプリケーションに受渡し
./mvnw clean package quarkus:dev -Dquarkus.oidc.client-id=$CLIENTID -Dquarkus.oidc.credentials.secret=$SECRET
```

## hello-page(Githubのログインが必要)
http://localhost:8080/web/hello-page?myname=kmotojim

http://localhost:8080/web/private-page?myname=新宿一郎

# シナリオ<2>

## Branch切り替え
```
git checkout scenario2
```

### application.properties編集
OIDCに関する設定の追加
```
# Permissionパスの指定(permit)
quarkus.http.auth.permission.permitted.paths=/web/not-protected-page
# 指定したPermissionパスのPolicyを指定
quarkus.http.auth.permission.permitted.policy=permit
```

## DEVELOPMENT機能で起動
```
# # quarkusを開発モードで機能 & -Dオプションで変数をアプリケーションに受渡し
./mvnw clean package quarkus:dev -Dquarkus.oidc.client-id=$CLIENTID -Dquarkus.oidc.credentials.secret=$SECRET
```

## protected-page(Githubのログインが必要)
http://localhost:8080/web/protected-page

## not-protected-page(Githubにログインせずにアクセス可能)
http://localhost:8080/web/not-protected-page