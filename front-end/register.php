<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cadastro - Vacina Mais</title>
  <link rel="stylesheet" href="css/register.css">
</head>
<body>
  <div class="container">
    <img src="img/logo.png" alt="Vacina Mais" class="logo">
    <h2>CADASTRO</h2>
    <p>Preencha com seus dados.</p>
    <form action="processar_registro.php" method="POST">
      <label for="name">Nome</label>
      <input type="text" id="name" name="name" placeholder="Digite seu nome completo aqui..." required>

      <label for="email">E-mail</label>
      <input type="email" id="email" name="email" placeholder="paciente@gmail.com" required>

      <label for="password">Senha</label>
      <input type="password" id="password" name="password" placeholder="Digite sua senha aqui..." required>

      <label for="confirm_password">Confirme sua senha</label>
      <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirme sua senha aqui..." required>

      <label for="cpf">CPF</label>
      <input type="text" id="cpf" name="cpf" placeholder="XXX.XXX.XXX-XX" required>

      <label for="dob">Data de nascimento</label>
      <input type="text" id="dob" name="dob" placeholder="XX/XX/XXXX" required>

      <label for="gender">Sexo</label>
      <input type="text" id="gender" name="gender" placeholder="Escreva seu gênero aqui..." required>

      <button type="submit">CONCLUIR</button>
    </form>
  </div>
</body>
</html>
