<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Cadastro Idoso</title>
  <link rel="stylesheet" href="css/loginidoso.css">
</head>
<body>
  <header>
    <h1>vacina + mais</h1>
  </header>
  <div class="container">
    <h2>CADASTRO IDOSO</h2>
    <form action="cadastro_idoso.php" method="post">
      <label for="nome">Nome:</label><br>
      <input type="text" id="nome" name="nome" required><br>
      <label for="telefone">Numero de Telefone:</label><br>
      <input type="tel" id="telefone" name="telefone" required><br>
      <label for="senha">Senha:</label><br>
      <input type="password" id="senha" name="senha" required><br>
      <label for="confirmar_senha">Confirme a Senha:</label><br>
      <input type="password" id="confirmar_senha" name="confirmar_senha" required><br>
      <label for="data_nascimento">Data de Nascimento:</label><br>
      <input type="date" id="data_nascimento" name="data_nascimento" required><br>
      <label for="sexo">Sexo:</label><br>
      <select id="sexo" name="sexo" required>
        <option value="Masculino">Masculino</option>
        <option value="Feminino">Feminino</option>
        <option value="Outro">Outro</option>
      </select><br>
      <input type="submit" value="CADASTRAR">
    </form>
  </div>
</body>
</html>
