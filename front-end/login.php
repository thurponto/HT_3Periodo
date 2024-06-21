<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="login.css">
</head>
<body>

  <h2>Login</h2>
  <form action="processar_login.php" method="post">
    <label for="username">Nome de usuário:</label>
    <input type="text" name="username" id="username" required><br><br>

    <label for="password">Senha:</label>
    <input type="password" name="password" id="password" required><br><br>

    <button type="submit">Entrar</button>
  </form>

  <p>Não tem uma conta? <a href="register.php">Cadastre-se agora!</a></p>

</body>
</html>