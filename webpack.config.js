const path = require('path');

const pcssEntryPoint = './resources/styles/main.css'

module.exports = {
    mode: 'development',
    entry: pcssEntryPoint,
    //by default the output will be directed to dist folder,
    // we need it into resources as we want it to be part of the classpath
    output: {
        path: path.resolve(__dirname, './resources/public'),
        // this will be ignored
        filename: './js-out/style-bundle.js',
    },

    module: {
        rules: [
            {
                test: /\.(css|pcss)$/i,
                exclude: /node_modules/,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: './css/main_out.css',
                        },
                    },
                    {
                        loader: 'extract-loader',
                        options: {publicPath: "../"}
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            importLoaders: 1,
                        }
                    },
                    {
                        loader: 'postcss-loader'
                    }
                ]
            },
            {
                test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: '[name].[ext]',
                            outputPath: 'fonts/'
                        }
                    }]
            }
        ]
    }
}