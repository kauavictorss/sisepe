document.addEventListener("DOMContentLoaded", function () {
    fetch("/menu.html")
        .then(response => response.text())
        .then(data => {
            document.getElementById("menu-placeholder").innerHTML = data;
            // Set active link
            const currentPage = window.location.pathname;
            const navLinks = document.querySelectorAll('.navbar-nav .nav-link');
            navLinks.forEach(link => {
                if (link.getAttribute('href') === currentPage) {
                    link.classList.add('active');
                    link.setAttribute('aria-current', 'page');
                }
            });

            // Add logout functionality
            const logoutButton = document.getElementById('logout-button');
            if (logoutButton) {
                logoutButton.addEventListener('click', (event) => {
                    event.preventDefault();
                    sessionStorage.removeItem('loggedIn');
                    window.location.href = '/login.html';
                });
            }
        });
});