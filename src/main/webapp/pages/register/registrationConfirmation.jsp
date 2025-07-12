<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
    <title>Registration confirmation</title>
        <meta charset="ISO-8859-1" />
        <title>Registration Status</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        />
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <h2 style="color: green; text-align: center">${message}</h2>
        <div class="container">
            <table class="table table-hover">
                <thead>
                    <tr bgcolor="gray">
                        <th>Employee Id</th>
                        <th>Employee Name</th>
                        <th>Salary</th>
                        <th>Email Address</th>
                        <th>Password</th>
                    </tr>
                </thead>
                <tbody>
                    <tr bgcolor="coffee">
                        <td>${employeeDTO.employeeId}</td>
                        <td>${employeeDTO.employeeName}</td>
                        <td>${employeeDTO.salary}</td>
                        <td>${employeeDTO.emailId}</td>
                        <td>${employeeDTO.password}</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- custom CSS -->
        <style>
            body {
                background-color: #f8f9fa;
            }

            .form-container {
                background-color: white;
                border-radius: 10px;
                padding: 30px;
                margin-top: 40px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }

            .center-message {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }

            .form-title {
                color: #007bff;
                font-weight: bold;
            }

            h2 {
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
            }
        </style>

        <!-- 🎆 Fireworks Canvas -->
        <canvas id="fireworksCanvas"></canvas>

        <!-- 🎇 Fireworks JavaScript -->
        <script>
            const canvas = document.getElementById("fireworksCanvas");
            const ctx = canvas.getContext("2d");

            canvas.width = window.innerWidth;
            canvas.height = window.innerHeight;
            canvas.style.position = "fixed";
            canvas.style.top = 0;
            canvas.style.left = 0;
            canvas.style.pointerEvents = "none";
            canvas.style.zIndex = 9999;

            let particles = [];

            function random(min, max) {
              return Math.random() * (max - min) + min;
            }

            function createFirework(x, y) {
              const colors = ["#ff4c4c", "#4cff4c", "#4c4cff", "#ffff4c", "#ff4cff"];
              for (let i = 0; i < 100; i++) {
                particles.push({
                  x: x,
                  y: y,
                  dx: random(-3, 3),
                  dy: random(-3, 3),
                  alpha: 1,
                  radius: random(1, 3),
                  color: colors[Math.floor(Math.random() * colors.length)]
                });
              }
            }

            function animate() {
              requestAnimationFrame(animate);
              ctx.clearRect(0, 0, canvas.width, canvas.height);

              particles.forEach((p, i) => {
                p.x += p.dx;
                p.y += p.dy;
                p.alpha -= 0.01;

                if (p.alpha <= 0) {
                  particles.splice(i, 1);
                }

                ctx.beginPath();
                ctx.arc(p.x, p.y, p.radius, 0, Math.PI * 2);
                ctx.fillStyle = p.color;
                ctx.globalAlpha = p.alpha;
                ctx.fill();
                ctx.globalAlpha = 1;
              });
            }

            // 🎇 Launch fireworks every 1.5 seconds in the center
            setInterval(() => {
              createFirework(window.innerWidth / 2, window.innerHeight / 2);
            }, 1500);

            animate();
        </script>
    </body>
</html>
