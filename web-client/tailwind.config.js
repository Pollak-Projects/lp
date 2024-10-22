import {nextui} from '@nextui-org/theme'

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './components/quizComponents/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
    './app/(quiz)/quiz/**/*.{js,ts,jsx,tsx,mdx}',
    './app/(restOfTheApp)/login/**/*.{js,ts,jsx,tsx,mdx}',
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
      /*backgroundImage: {
        'custom-bg': "url('@/accessories/Background.jpg')",
        'custom-bg-sm': "url('@/accessories/Background.jpg')",
        'custom-bg-lg': "url('@/accessories/Background.jpg')",
      },*/
    },
    colors: {
      'gradientPurple': '#9353D3',
      'gradientBlue': '#61a0d4',

    },
  },
  darkMode: "class",
  plugins: [nextui({
    themes: {
      dark: {
        colors: {
          background: "#468BCB",
          content1: "#191919"

        }
      }
    }
  })],
}
