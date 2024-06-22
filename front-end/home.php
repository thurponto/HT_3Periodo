<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clínica de Vacinas - Unimed Noroeste do Paraná</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <div class="container">
            <a href="/">
                <img src="logo.png" alt="Logo Unimed Noroeste do Paraná">
            </a>
            <nav>
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href="/sobre">Sobre</a></li>
                    <li><a href="/contato">Contato</a></li>
                </ul>
            </nav>
            <div class="contato">
                <p>SAC: 0800 041 4554</p>
                <a href="#" class="button">Login</a>
            </div>
        </div>
    </header>

    <main>
        <section class="hero">
            <div class="container">
                <h1>Clínica de Vacinas</h1>
                <p>Sua saúde em primeiro lugar.</p>
                <a href="#" class="button">Agendar Vacinação</a>
            </div>
            <img src="hero-image.jpg" alt="Imagem da clínica de vacinas">
        </section>

        <section class="sobre">
            <div class="container">
                <h2>Sobre a Clínica</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quaerat, ullam accusamus labore aliquid distinctio beatae accusantium fugiat. Odit, quisquam. Quidem aliquid laborum ullam voluptatem accusantium doloribus! Quaerat, magni cumque minima voluptatum.</p>
            </div>
        </section>

        <section class="servicos">
            <div class="container">
                <h2>Serviços</h2>
                <div class="servicos-grid">
                    <div class="servico">
                        <img src="vacina-1.jpg" alt="Vacina 1">
                        <h3>Vacina 1</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Reprehenderit, voluptatum.</p>
                    </div>
                    <div class="servico">
                        <img src="vacina-2.jpg" alt="Vacina 2">
                        <h3>Vacina 2</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Reprehenderit, voluptatum.</p>
                    </div>
                    <div class="servico">
                        <img src="vacina-3.jpg" alt="Vacina 3">
                        <h3>Vacina 3</h3>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Reprehenderit, voluptatum.</p>
                    </div>
                </div>
            </div>
        </section>

       
    </main>

    <footer>
    <section class="contato">
            <div class="container">
                <h2>Fale Conosco</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quaerat, ullam accusamus laborealiquid distinctio beatae accusantium fugiat. Odit, quisquam. Quidem aliquid laborum ullam voluptatem accusantium doloribus! Quaerat, magni cumque minima voluptatum.</p>
                <form>
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome">
                    <label for="email">E-mail:</label>
                    <input type="email" id="email" name="email">
                    <label for="mensagem">Mensagem:</label>
                    <textarea id="mensagem" name="mensagem"></textarea>
                    <button type="submit">Enviar</button>
                </form>
            </div>
        </section>
        <div class="container">
            <p>&copy; 2023 Clínica de Vacinas - Unimed Noroeste do Paraná</p>
        </div>
    </footer>

    <script src="script.js"></script>
</body>
</html>