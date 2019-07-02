#! /usr/bin/env node

'use strict'

const importJsx = require('import-jsx');
const { render } = require('ink');
const showBanner = require('node-banner');
const React = require('react');

const gamePlay = importJsx('./gameplay');
const { showHelpInformation, showVersionInformation, showUnknownOptionInformation, showInvalidArgsInformation } = require('./utils/helpers');

// parse args
const [, , ...args] = process.argv;

if (!args.length) {
  process.stdout.write('\u001B[2J\u001B[0;0f');
  (async () => {
  	await showBanner('Handcricket');
  	render(React.createElement(gamePlay));
  })();
} else if (args.length > 1 || !args[0].includes('-')) {
    showInvalidArgsInformation();
} else if (args[0] === '--help' || args[0] === '-h') {
    showHelpInformation();
} else if (args[0] === '--version' || args[0] === '-V') {
    showVersionInformation();
} else {
    showUnknownOptionInformation(args[0]);
}
