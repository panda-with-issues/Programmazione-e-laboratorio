;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname complemento_a_1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define compl-1   ; val: "0" se "1" e viceversa
  (lambda (bit)   ; bit: stringa "0" o "1"
    (if (string=? bit "0")
        "1"
        "0"
      )
    ))

(define compl-dx  ; val: stringa di bit complementata
  (lambda (str)   ; str: stringa di bit
    (if (<= (string-length str) 1)
        (compl-1 str)
        (string-append
          (compl-dx (substring str 0 (- (string-length str) 1)))
          (compl-1 (substring str (- (string-length str) 1)))
          )
      )
    ))

(define punto-medio ; val: indice (intero positivo)
  (lambda (str)     ; str: stringa
    (if (= (remainder (string-length str) 2) 0)
        (/ (string-length str) 2)
        (/ (- (string-length str) 1) 2)
        )
    ))

(define bin-compl ; val: stringa di bit complementata
  (lambda (str)   ; str: stringa di bit
    ; caso base
    (if (= (string-length str) 1)
        (compl-1 str)
        ; passo ricorsivo
        (string-append
          (compl-dx (substring str 0 (punto-medio str)))
          (compl-dx (substring str (punto-medio str)))
          )
        )
    ))