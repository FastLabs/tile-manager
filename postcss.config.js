//require('precss');
//require('autoprefixer');
//require('tailwindcss/nesting');

module.exports = {
    plugins: [
        require('tailwindcss/nesting'),
        require('tailwindcss'),
        require('autoprefixer'),
    ]
}