"use client";
import Loading from "@/app/loading";
import { PaletteMode, useMediaQuery } from "@mui/material";
import {
  Theme,
  ThemeProvider
} from "@mui/material/styles";
import React, {
  createContext,
  ReactNode,
  useContext,
  useEffect,
  useMemo,
  useState,
} from "react";

interface ThemeContextType {
  colorMode: PaletteMode;
  toggleColorMode: () => void;
}

const ThemeContext = createContext<ThemeContextType | undefined>(undefined);

interface CustomThemeProviderProps {
  children: ReactNode;
}

export const CustomThemeProvider: React.FC<CustomThemeProviderProps> = ({
                                                                          children,
                                                                        }) => {
  const [colorMode, setColorMode] = useState<PaletteMode>("light");
  const [loading, setLoading] = useState(true);
  const sysPreferDark = useMediaQuery("(prefers-color-scheme: dark)", {
    noSsr: true,
  });

  // This prop determines native elements theme
  const setColorSchemeOnHtml = (newMode: PaletteMode) =>
    document.documentElement.style.setProperty("color-scheme", newMode);

  // First-load color-mode routine: use stored OR system-preference OR fallback to 'light'
  // Set it on state+themecontext AND on <html>'s color-scheme style prop
  useEffect(() => {
    const storedColorMode = localStorage.getItem("colorMode") as PaletteMode;
    const newMode = storedColorMode || (sysPreferDark ? "dark" : "light");
    setColorMode(newMode);
    setColorSchemeOnHtml(newMode);
    setLoading(false);
  }, [sysPreferDark]);

  const theme: Theme = useMemo(() => {
    // ... mui createStyles()
  }, [colorMode]);

  // User-triggered color-mode change: set preference on storage and html
  const toggleColorMode = () => {
    setColorMode((prevMode) => {
      const newMode = prevMode === "light" ? "dark" : "light";
      localStorage.setItem("colorMode", newMode);
      setColorSchemeOnHtml(newMode);
      return newMode;
    });
  };

  // on first-load the loading screen is immediatelly displayed
  // until the theme is loaded with the uptodate color-mode
  if (loading) {
    return <Loading />;
  }

  return (
    <ThemeContext.Provider value={{ colorMode, toggleColorMode }}>
      <ThemeProvider theme={theme}>{children}</ThemeProvider>
    </ThemeContext.Provider>
  );
};

export const useCustomThemeContext = (): ThemeContextType => {
  const context = useContext(ThemeContext);
  if (!context) {
    throw new Error("useCustomTheme must be used within a CustomThemeProvider");
  }
  return context;
};