<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        :root {
            --background-gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            --card-background: rgba(255, 255, 255, 0.15);
            --text-primary: #ffffff;
            --text-secondary: rgba(255, 255, 255, 0.7);
            --input-background: rgba(255, 255, 255, 0.1);
            --border-color: rgba(255, 255, 255, 0.2);
            --button-gradient: linear-gradient(to right, #6a11cb 0%, #2575fc 100%);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', sans-serif;
            background: var(--background-gradient);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: var(--text-primary);
        }

        .login-container {
            width: 100%;
            max-width: 400px;
            background: var(--card-background);
            border-radius: 20px;
            padding: 40px;
            backdrop-filter: blur(10px);
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            border: 1px solid var(--border-color);
        }

        .login-title {
            text-align: center;
            font-size: 2.5rem;
            margin-bottom: 30px;
            font-weight: 600;
            color: var(--text-primary);
        }

        .login-form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .login-input {
            width: 100%;
            padding: 15px;
            background: var(--input-background);
            border: 1px solid var(--border-color);
            border-radius: 10px;
            color: var(--text-primary);
            font-size: 1rem;
            outline: none;
        }

        .login-input::placeholder {
            color: var(--text-secondary);
        }

        .login-button {
            background: var(--button-gradient);
            color: var(--text-primary);
            border: none;
            padding: 15px;
            border-radius: 10px;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1 class="login-title">Connexion</h1>
        <form class="login-form" action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <input 
                type="email" 
                class="login-input" 
                placeholder="Adresse email" 
                name="email" 
                required
            >
            
            <input 
                type="password" 
                class="login-input" 
                placeholder="Mot de passe" 
                name="password" 
                required
            >
            
            <button type="submit" class="login-button">
                Se connecter
            </button>
        </form>
    </div>
</body>
</html>