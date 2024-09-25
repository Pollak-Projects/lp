import {nextui} from '@nextui-org/theme'

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
    './node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}',
    './src/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ["var(--font-sans)"],
        mono: ["var(--font-geist-mono)"],
        inter: ["var(--font-)"]
      },
    },
    colors: {
      'gradientPurple': '#9353D3',
      'gradientBlue': '#61a0d4',

    },
    backgroundImage: {
      'my-gradient': 'linear-gradient(102deg, rgba(3, 5, 29, 0.85) 2.11%, rgba(255, 0, 0, 0.85) 100%)',
    }
  },
  darkMode: "class",
  plugins: [nextui({
    themes: {
      dark: {
        colors: {
          background: "#468BCB",
          content1: "#191919",
          secondary: "#191919"

        }
      }
    }
  })],
}
