;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname plurali) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define p-eng  ; val: string at plural
 (lambda (str) ; str: a string
   (string-append str "s")
   )
  )
(define p-f-it  ; val: string at plural ending in "-e"
  (lambda (str) ; str: an italian feminine word ending in "-a"
    (string-append (substring str 0 (- (string-length str) 1)) "e")
    )
  )
(define is-second-conj? ; val: boolean
  (lambda (str)         ; str: an italian verb at the infinite tense
    (char=? (string-ref str (- (string-length str) 3)) #\e)
    )
  )