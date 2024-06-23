<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="css/login.css">
</head>
<body>
  <header>
    <div class="dropdown">
      <button class="dropbtn">⋮</button>
      <div class="dropdown-content">
        <a href="register.php">Cadastro</a>
        <a href="login.php">Login</a>
        <a href="#">Acessibilidade</a>
      </div>
    </div>
    <h1>Portal de Saúde</h1>
  </header>
  <div class="container">
    <h2>Login</h2>
    <form action="processar_login.php" method="POST">
      <label for="username">Usuário:</label>
      <input type="text" id="username" name="username" required>
      <label for="password">Senha:</label>
      <input type="password" id="password" name="password" required>
      <button type="submit">Login</button>
    </form>
  </div>
</body>
</html>
