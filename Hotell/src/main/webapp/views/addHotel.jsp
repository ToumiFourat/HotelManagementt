<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Hôtel</title>
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

        .form-container {
            width: 100%;
            max-width: 500px;
            background: var(--card-background);
            border-radius: 20px;
            padding: 40px;
            backdrop-filter: blur(10px);
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            border: 1px solid var(--border-color);
        }

        .form-title {
            text-align: center;
            font-size: 2.5rem;
            margin-bottom: 30px;
            font-weight: 600;
            color: var(--text-primary);
        }

        .form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .form-input, .form-textarea, .form-file {
            width: 100%;
            padding: 15px;
            background: var(--input-background);
            border: 1px solid var(--border-color);
            border-radius: 10px;
            color: var(--text-primary);
            font-size: 1rem;
            outline: none;
        }

        .form-input::placeholder, .form-textarea::placeholder {
            color: var(--text-secondary);
        }

        .form-textarea {
            resize: none;
            height: 100px;
        }

        .form-button {
            background: var(--button-gradient);
            color: var(--text-primary);
            border: none;
            padding: 15px;
            border-radius: 10px;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
        }
          select {
    padding: 15px;
    width: 100%; /* S'adapte au conteneur */
    background: var(--input-background);
    border: 1px solid var(--border-color);
    border-radius: 10px;
    color: var(--text-primary); /* Texte clair pour contraste */
    font-size: 1rem;
    outline: none;
    appearance: none; /* Supprime l'apparence native */
    cursor: pointer;
    transition: all 0.3s ease; /* Animation fluide */
}

select:hover {
    border-color: #6a11cb; /* Bordure en survol */
}

select:focus {
    border-color: #2575fc;
    box-shadow: 0 0 8px rgba(37, 117, 252, 0.5); /* Effet lumineux */
}

select option {
    color: #333; /* Texte des options */
    background-color: #fff; /* Fond blanc pour les options */
}
    </style>
</head>
<body>
    <div class="form-container">
        <h1 class="form-title">Ajouter un Hôtel</h1>
        <form class="form" action="${pageContext.request.contextPath}/AddHotelServlet" method="post" enctype="multipart/form-data">
            <input 
                type="text" 
                class="form-input" 
                placeholder="Nom de l'Hôtel" 
                name="name" 
                required
            >
            <select name="city" required>
                    <option value="Ariana">ARIANA</option>
                    <option value="Sousse">SOUSSE</option>
                    <option value="Monastir">MONASTIR</option>
                    <option value="ZARZIS">ZARZIS</option>
                </select>
            <input 
                type="number" 
                class="form-input" 
                placeholder="Nombre d'Étoiles (1-5)" 
                name="stars" 
                min="1" 
                max="5" 
                required
            >
            <textarea 
                class="form-textarea" 
                placeholder="Description de l'Hôtel" 
                name="description" 
                required
            ></textarea>
            <input 
                type="file" 
                class="form-file" 
                name="image" 
                accept="image/*" 
                required
            >
            <button type="submit" class="form-button">Ajouter</button>
        </form>
    </div>
</body>
</html>
