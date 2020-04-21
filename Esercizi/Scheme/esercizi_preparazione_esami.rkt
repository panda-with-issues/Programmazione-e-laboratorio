;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname esercizi_preparazione_esami) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))

;; Cifrario di Vineger
;; Messaggio in chiaro: "ALEA IACTA EST JULIUS CAESAR DIXIT"
;; Chiave: "JULISCAER"

(define vineger           ; val: str
  (lambda (msg key rot)   ; msg: str, key: str, rot: letter->letter
    (if (string=? msg "")
        ""
        (string-append (string (rot (string-ref msg 0) (string-ref key 0)))
                       (vineger (substring msg 1)
                                (string-append (substring key 1) (substring key 0 1))
                                rot)
                       )
        )
    ))

(define rot
  (lambda (letter key-char)
    (let ((offset (- (char->integer key-char) (char->integer #\A))))
      (let ((crypted (+ (char->integer letter) offset)))
        (if (< crypted (char->integer #\Z))
            (integer->char crypted)
            (integer->char (- crypted 26))
            )
        )
      )
    ))

(vineger "ALEAIACTAESTJULIUSCAESARDIXIT" "JULISCAER" rot)

;; Scrivere una procedura che descriva la funzione H(f(x), g(x)) = max(f(x), g(x))

(define max-f
  (lambda (f g)
    (lambda (x)
      (if (>= (f x) (g x))
          (f x)
          (g x)
          )
      )))

;; procedura (palindrome lev "str") che determina il livello di palondrimicità di una parola

(define palindrome-lev
  (lambda (str)
    (cond ((string=? str "")
           0
           )
          ((= (string-length str) 1)
           1
           )
          ((char=? (string-ref str 0) (string-ref str (- (string-length str) 1)))
           (+ 1 (palindrome-lev (substring str 1 (- (string-length str) 1))))
           )
          (else
           (palindrome-lev (substring str 1 (- (string-length str) 1)))
           )
          )
    ))

(palindrome-lev "") ;0
(palindrome-lev "esose") ;3
(palindrome-lev "a") ;1
(palindrome-lev "erodere") ;3
(palindrome-lev "nono") ; 0
(palindrome-lev "ilredevevederli") ; 8

; procedura (palindrome? str) che dice se str è o no palindroma

(define palindrome?
  (lambda (str)
    (cond ((<= (string-length str) 1)
           #t
           )
          ((char=? (string-ref str 0) (string-ref str (- (string-length str) 1)))
           (palindrome? (substring str 1 (- (string-length str) 1)))
           )
          (else
           #f
          )
          )
    ))
(palindrome? "") ;true
(palindrome? "erodere") ;false
(palindrome? "a") ;true
(palindrome? "ilredevevederli") ;true
(palindrome? "nono") ;false
(palindrome? "acetonellenoteca") ;true

;; procedura (powers-of-two n) che restituisce la lista delle potenze di due che sommate restituiscono n

(define powers-of-two
 (lambda (n)
   (powers-rec n 0)
   ))

(define powers-rec
  (lambda (n i)
    (if (= n 0)
        null
        (cons (* (remainder n 2) (expt 2 i))
              (powers-rec (quotient n 2) (+ 1 i))
              )
        )
        
    ))
