<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Portal de Saúde</title>
  <link rel="stylesheet" href="css/home.css">
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
    <?php
    if (isset($_SESSION['user_id'])) {
      echo "<h2>Bem-vindo, ". $_SESSION['username']. "!</h2>";
      echo "<br><a href='logout.php'>Sair</a>";
    } else {
      echo "<h2>Você não está logado.</h2><br>";
      echo "<p><a href='login.php'>Login</a> ou <a href='register.php'>Cadastro</a></p>";
    }
    ?>
  </div>
  <div class="carousel">
    <div class="slides">
        <div class="slide">
            <img src="vacina-1.jpg" alt="Vacina 1" />
            <p>Vacina 1</p>
        </div>
        <div class="slide">
            <img src="vacina-2.jpg" alt="Vacina 2" />
            <p>Vacina 2</p>
        </div>
        <div class="slide">
            <img src="vacina-3.jpg" alt="Vacina 3" />
            <p>Vacina 3</p>
        </div>
        <div class="slide">
            <img src="vacina-4.jpg" alt="Vacina 4" />
            <p>Vacina 4</p>
        </div>
    </div>
    <button class="prev" onclick="plusSlides(-1)">&#10094;</button>
    <button class="next" onclick="plusSlides(1)">&#10095;</button>
  </div>
  <script src="carousel.js"></script>
</body>
</html>
