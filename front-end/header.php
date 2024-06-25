<?php
    // session_start();
    // if (isset($_GET['logout'])) {
    //     session_destroy();
    //     exit;
    // }

    // $usuarioLogado = isset($_SESSION['usuario']);

    // if (!$usuarioLogado && basename($_SERVER['PHP_SELF']) != 'login.php') {
    //     exit;
    // }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADMIN</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Certifique-se de ajustar o caminho -->
</head>
<body id="page-top">
    <header>
        <nav>
            <ul>
                <li>
                    <a href="?logout">Deslogar</a>
                </li>
                <li>
                    <a href="pages/vacinas">Importância das vacinas</a>
                </li>
            </ul>
        </nav>
    </header>
</body>
</html>
