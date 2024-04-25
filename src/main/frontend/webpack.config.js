// webpack.config.js
const path = require('path');
const glob = require('glob')
const EnvironmentPlugin = require('webpack/lib/EnvironmentPlugin');

const prefix = './js';

const output = '../resources/static';

module.exports = ({ NODE_ENV }) => ({
    mode: NODE_ENV,
    target: ['web', 'es5'],
    entry: {
        ...glob.sync(`${prefix}/**/*.js`).reduce((entries, file) => {
            const entryKey = file.replace(prefix, '');
            const entryName = entryKey.replace(/\//g, '_').replace('.js', '');
            entries[entryName] = `./${file}`;
            return entries;
        }, {})
    },
    output: {
        path: path.resolve(__dirname, output),
        filename: '[name].js',
    },
    resolve: {
        alias: {
            '~': path.resolve(__dirname, 'src'),
        },
        extensions: ['.js'],
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /[\\/]node_modules[\\/]/,
                loader: 'babel-loader',
            },
        ],
    },
    plugins: [
        new EnvironmentPlugin({
            NODE_ENV,
        }),
    ],
    devtool: false,
});