<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <title> Database Management System </title>
  <meta name="author" content="Hengyi Li" />
  <link rel="icon" href="../Resources/favicon.png">
  <link rel="stylesheet" href="managewords_portal.css" />
</head>

<body>
  <div>
    <p> You are already logged in </p>
    <?php
    if ($_SESSION['is_administrator'] == 1)
    {
    ?>
      <button type="submit" class="submit" id="admin_page">
        Take me to the main page
      </button>
      <script src="../JumpJS/admin_jump.js"></script>
    <?php
    }
    else
    {
    ?>
      <button type="submit" class="submit" id="user_page">
        Take me to the main page
      </button>
      <script src="../JumpJS/user_jump.js"></script>
    <?php
    }
    ?>
  </div>
</body>
</html>
