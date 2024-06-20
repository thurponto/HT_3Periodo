<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Cadastro</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>

  <h2>Cadastro</h2>
  <form action="processar_registro.php" method="post">
    <label for="username">Nome de usuário:</label>
    <input type="text" name="username" id="username" required><br><br>

    <label for="password">Senha:</label>
    <input type="password" name="password" id="password" required><br><br>

    <label for="email">E-mail:</label>
    <input type="email" name="email" id="email" required><br><br>

    <button type="submit">Cadastrar</button>
  </form>

  <p>Já tem uma conta? <a href="login.php">Faça login agora!</a></p>

</body>
</html>