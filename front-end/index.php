<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Página Inicial</title>
  <link rel="stylesheet" href="index.css">
</head>
<body>
  <div class="container">
    <?php
    session_start();

    // Verificar se o usuário está logado
    if (isset($_SESSION['user_id'])) {
      echo "<h1>Bem-vindo, ". $_SESSION['username']. "!</h1>";
      echo "<br><a href='logout.php'>Sair</a>";
    } else {
      echo "<h1>Você não está logado.</h1><br>";
      echo "<p><a href='login.php'>Login</a> ou <a href='register.php'>Cadastro</a></p>";
    }
    ?>
  </div>
</body>
</html>
