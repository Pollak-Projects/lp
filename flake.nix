{
  description = "Developer shell for lp";
  inputs = {
    nixpkgs.url = "nixpkgs/nixos-unstable";
  };
  outputs = { self, nixpkgs, ... }:
    let
      system = "x86_64-linux";
      pkgs = import nixpkgs { inherit system; };
    in
    rec {

      devShells."${system}".default = pkgs.mkShell {
        buildInputs = with pkgs; [
          maven
          jdk22
          jre8
          nodejs
        ];
      };
    };

}
