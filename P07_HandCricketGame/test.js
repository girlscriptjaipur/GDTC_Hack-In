const assert = require('assert')
const execa = require('execa')
const path = require('path')
const { version } = require('./package')

// holds reference to the root command
const scriptName = 'handcricket'

const helpInformation = ` Usage: $ ${scriptName} [options]
        
        Options
        -h | --help: Shows up help information
        -V | --version: Shows up verison information
    `

const invalidArgsText = ` Invalid arguments provided

 See ${scriptName} --help for the list of available options.
	`

// path to the root command
const rootCmd = path.join(process.cwd(), 'index.js')

const matchSnapShot = (expected, actual) =>
  assert.equal(expected, actual.trim())

describe('Passing in -v and --version', () => {
  it('Should show up version information', () => {
    ;['-v', '--version'].forEach(arg => {
      const { stdout } = execa.commandSync(`${rootCmd} ${arg}`)
      matchSnapShot(version, stdout.trim())
    })
  })
})

describe('Passing in -h and --help', () => {
  it('Should show up help information', () => {
    ;['-h', '--help'].forEach(arg => {
      const { stdout } = execa.commandSync(`${rootCmd} ${arg}`)
      matchSnapShot(helpInformation.trim(), stdout.trim())
    })
  })
})

describe('Passing in an invalid argument', () => {
  it('Should warn as invalid args were passed', () => {
    const { stdout } = execa.commandSync(`${rootCmd} junkcmd`)
    matchSnapShot(invalidArgsText.trim(), stdout.trim())
  })
})

describe('Passing in an unknown option', () => {
  it('Should warn as invalid option was passed', () => {
    const { stdout } = execa.commandSync(`${rootCmd} -invalid`)
    assert.ok(stdout.includes('Unknown option'))
  })
})
